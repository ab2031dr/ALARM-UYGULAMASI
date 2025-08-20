document.querySelectorAll('.tab-btn').forEach(btn=>{
btn.addEventListener('click', ()=>{
  document.querySelectorAll('.tab-btn').forEach(b=>b.classList.remove('active'));
  btn.classList.add('active');
  document.querySelectorAll('.page').forEach(p=>p.style.display='none');
  document.getElementById(btn.dataset.target).style.display='block';
});
});

document.querySelectorAll('#daysPicker .day').forEach(d=>{
d.addEventListener('click', ()=>{ d.classList.toggle('active'); });
});

let alarms = JSON.parse(localStorage.getItem('alarms')||'[]');
const alarmsListEl = document.getElementById('alarmsList');

function persist(){ localStorage.setItem('alarms',JSON.stringify(alarms)); }

function render(){
  alarmsListEl.innerHTML='';
  if(alarms.length===0) alarmsListEl.innerHTML='<div>Henüz alarm yok.</div>';
  alarms.forEach((a,i)=>{
    const row = document.createElement('div');
    row.textContent = a.time + " - " + a.label;
    row.addEventListener('click', ()=>{
      // düzenleme logic
      document.getElementById('timeInput').value = a.time;
      document.getElementById('labelInput').value = a.label;
      alarms.splice(i,1);
      persist();
      render();
      document.querySelector('[data-target="tab-set"]').click();
    });
    alarmsListEl.appendChild(row);
  });
}

render();

document.getElementById('saveBtn').addEventListener('click', ()=>{
  const time = document.getElementById('timeInput').value;
  const label = document.getElementById('labelInput').value;
  if(time){ alarms.push({time,label}); persist(); render(); }
});
